package com.lenicliu.jboot.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@ExposesResourceFor(Post.class)
public class PostController {

	private static AtomicLong		ID		= new AtomicLong(1);
	private static Map<Long, Post>	POSTS	= new HashMap<Long, Post>();

	static {
		Post post1 = new Post();
		post1.setAuthor("lenicliu");
		post1.setCreated(new Date());
		post1.setId(ID.getAndIncrement());
		post1.setState(Post.State.PUBLISH);
		post1.setTitle("Hi, Hateoas");
		POSTS.put(post1.getId(), post1);

		Post post2 = new Post();
		post2.setAuthor("kitty");
		post2.setCreated(new Date());
		post2.setId(ID.getAndIncrement());
		post2.setState(Post.State.DRAFT);
		post2.setTitle("Spring-Boot-Hateoas");
		POSTS.put(post2.getId(), post2);
		
		Post post3 = new Post();
		post3.setAuthor("lenic & kitty");
		post3.setCreated(new Date());
		post3.setId(ID.getAndIncrement());
		post3.setState(Post.State.PUBLISH);
		post3.setTitle("I don't care");
		POSTS.put(post3.getId(), post3);
	}

	private <T> HttpEntity<T> ok(T value) {
		return new ResponseEntity<T>(value, HttpStatus.OK);
	}

	private <T> HttpEntity<T> ok() {
		return new ResponseEntity<T>(HttpStatus.OK);
	}

	private <T> HttpEntity<T> blank() {
		return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
	}

	private <T> HttpEntity<T> notFound() {
		return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<Resources<Resource<Post>>> get() {
		List<Post> posts = new ArrayList<Post>(POSTS.values());
		if (posts == null || posts.isEmpty()) {
			return blank();
		}
		Link _self = linkTo(methodOn(getClass()).get()).withSelfRel();
		return ok(new Resources<Resource<Post>>(linkPosts(posts), _self));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<Resource<Post>> get(@PathVariable Long id) {
		Post post = POSTS.get(id);
		if (post == null || post.getId() == null) {
			return notFound();
		}
		return ok(linkPost(post));
	}

	@RequestMapping(method = RequestMethod.POST)
	public HttpEntity<Resource<Post>> post(@RequestBody Post post) {
		post.setId(ID.getAndIncrement());
		post.setCreated(new Date());
		POSTS.put(post.getId(), post);
		return ok(linkPost(post));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Resource<Post>> put(@PathVariable Long id, @RequestBody Post post) {
		Post exist = POSTS.remove(id);
		if (exist == null) {
			return notFound();
		}
		exist.setAuthor(post.getAuthor());
		exist.setState(post.getState());
		exist.setTitle(post.getTitle());
		POSTS.put(exist.getId(), exist);
		return ok(linkPost(exist));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpEntity<Resource<Post>> delete(@PathVariable Long id) {
		Post removed = POSTS.remove(id);
		if (removed == null || removed.getId() == null) {
			return notFound();
		}
		return ok();
	}

	private Resource<Post> linkPost(Post post) {
		return linkPost(post, null, null);
	}

	private Resource<Post> linkPost(Post post, Post next, Post prev) {
		Resource<Post> resource = new Resource<Post>(post);
		resource.add(linkTo(methodOn(getClass()).get(post.getId())).withSelfRel());
		resource.add(linkTo(methodOn(getClass()).get()).withRel("list"));
		resource.add(linkTo(methodOn(getClass()).post(post)).withRel("create"));
		resource.add(linkTo(methodOn(getClass()).put(post.getId(), post)).withRel("modify"));
		resource.add(linkTo(methodOn(getClass()).delete(post.getId())).withRel("remove"));
		if (next != null && next.getId() != null) {
			resource.add(linkTo(methodOn(getClass()).get(next.getId())).withRel("next"));
		}
		if (prev != null && prev.getId() != null) {
			resource.add(linkTo(methodOn(getClass()).get(prev.getId())).withRel("prev"));
		}
		return resource;
	}

	@SuppressWarnings("serial")
	private Collection<Resource<Post>> linkPosts(List<Post> posts) {
		return new ArrayList<Resource<Post>>() {
			{
				for (int i = 0, n = posts.size(); i < n; i++) {
					Post post = posts.get(i);
					Post next = i + 1 < n ? posts.get(i + 1) : null;
					Post prev = i - 1 < 0 ? null : posts.get(i - 1);
					add(linkPost(post, next, prev));
				}
			}
		};
	}
}