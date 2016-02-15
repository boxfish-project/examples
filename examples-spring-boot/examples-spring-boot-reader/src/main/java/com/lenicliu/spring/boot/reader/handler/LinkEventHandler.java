package com.lenicliu.spring.boot.reader.handler;

import java.text.SimpleDateFormat;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.lenicliu.spring.boot.reader.entity.Item;
import com.lenicliu.spring.boot.reader.entity.Link;
import com.lenicliu.spring.boot.reader.repository.ItemRepository;
import com.lenicliu.spring.boot.reader.repository.LinkRepository;

@Component
@RepositoryEventHandler
public class LinkEventHandler {

	@Autowired
	private ItemRepository	itemRepository;
	@Autowired
	private LinkRepository	linkRepository;
	private RestTemplate	template	= new RestTemplate();

	@HandleBeforeCreate
	@SuppressWarnings("unchecked")
	public void beforeCreateLink(Link link) throws Exception {
		String url = link.getUrl();
		Link existingLink = linkRepository.findByUrl(url);
		if (existingLink != null) {
			throw new RuntimeException(url + " existing");
		}
		String xml = template.getForEntity(url, String.class).getBody();
		Document feed = DocumentHelper.parseText(xml);
		Node rss = feed.selectSingleNode("rss");
		List<Node> channels = rss.selectNodes("channel");
		for (Node channel : channels) {
			link.setTitle(channel.selectSingleNode("title").getText());
			List<Node> items = channel.selectNodes("item");
			for (Node node : items) {
				Item item = new Item();
				item.setAuthor(node.selectSingleNode("author") == null ? null : node.selectSingleNode("author").getText());
				item.setCreated(node.selectSingleNode("pubDate") == null ? null : new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z").parse(node.selectSingleNode("pubDate").getText()));
				item.setGuid(node.selectSingleNode("guid").getText());
				item.setLink(node.selectSingleNode("link").getText());
				item.setTitle(node.selectSingleNode("title").getText());
				Item existing = itemRepository.findByGuid(item.getGuid());
				if (existing == null) {
					itemRepository.save(item);
				}
			}
		}
	}
}