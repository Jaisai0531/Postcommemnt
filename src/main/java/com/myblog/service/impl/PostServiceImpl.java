package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.payload.PostDetailsDto;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
    private PostRepository  postRepository;
    private CommentRepository commentRepository;
    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {

        this.postRepository = postRepository;
        this.commentRepository=commentRepository;
    }
    
    @Autowired
    ModelMapper mapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);
       PostDto newPostDto =mapToDto(newPost);
        return newPostDto;
    }

    @Override
    public PostResponse getallPost(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> posts = postRepository.findAll(pageable);//*Find ALl*---> Method will return Page of objects.
        List<Post> content = posts.getContent();//getContent Method Will Give List

        PostResponse postResponse = new PostResponse();

        List<PostDto> postdtos = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        postResponse.setContent(postdtos);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        postResponse.setPageSize(posts.getSize());
        return postResponse ;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id", id)//
        );
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
       post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedpost = postRepository.save(post);
        return mapToDto(updatedpost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)

        );
        postRepository.delete(post);
    }

    @Override
    public List<CommentDto> getCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        List<Comment> comments = post.getComments();

        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setName(comment.getName());
            commentDto.setEmail(comment.getEmail());
            commentDto.setBody(comment.getBody());

            commentDtos.add(commentDto);
        }

        return commentDtos;
    }

    @Override
    public PostDetailsDto getPostDetailsById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> commentDtos = new ArrayList<>();

        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setName(comment.getName());
            commentDto.setEmail(comment.getEmail());
            commentDto.setBody(comment.getBody());

            commentDtos.add(commentDto);
        }

        PostDetailsDto postDetailsDto = new PostDetailsDto();
        postDetailsDto.setId(post.getId());
        postDetailsDto.setTitle(post.getTitle());
        postDetailsDto.setDescription(post.getDescription());
        postDetailsDto.setContent(post.getContent());
        postDetailsDto.setComments(commentDtos);

        return postDetailsDto;
    }



    PostDto mapToDto(Post post) {  //Converting Entity to Dto
        PostDto postDto = mapper.map(post, PostDto.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getTitle());
        return postDto;
    }
    //Convert DtoToEntity
        Post mapToEntity(PostDto postDto) {//Job of this method to conevert Dto To Entity 
            Post post = mapper.map(postDto, Post.class);
//            Post post = new Post();
//            post.setId(postDto.getId());
//            post.setTitle(postDto.getTitle());
//            post.setContent(postDto.getContent());
//            post.setDescription(postDto.getDescription());
            return post;
        }
        //ConvertEntityToDto
    }

