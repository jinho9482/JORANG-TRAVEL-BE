//package com.example.travel_diary.global.config;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.elasticsearch.core.GetResponse;
//import co.elastic.clients.elasticsearch.core.IndexResponse;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import co.elastic.clients.transport.ElasticsearchTransport;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import com.example.travel_diary.global.domain.entity.Post;
//import com.example.travel_diary.global.domain.repository.PostRepository;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.Header;
//import org.apache.http.HttpHost;
//import org.apache.http.message.BasicHeader;
//import org.elasticsearch.client.RestClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@Slf4j
//@RestController
//@RequestMapping("/ela")
//@RequiredArgsConstructor
//public class ElasticSearchConfig {
//
//    private final PostRepository postRepository;
//    @GetMapping
//    public void elastic() throws IOException {
//        // URL and API key
//        String serverUrl = "http://localhost:9200";
//        String apiKey = "Nkh6MlhaQUIxTmhRTVJ5d21pU2c6cXBSQm9WX2hUcmk5QWM4QVdObVRJdw==";
////        String apiKey = "Nm55UlhwQUIxTmhRTVJ5d0xDU206V1c5ZmoxME1SREtSYlU2enNUWGUzQQ==";
//
//        // Create the low-level client
//        RestClient restClient = RestClient
//                .builder(HttpHost.create(serverUrl))
//                .setDefaultHeaders(new Header[]{
//                        new BasicHeader("Authorization", "ApiKey " + apiKey)
//                })
//                .build();
//        System.out.println(1);
//        // Create the transport with a Jackson mapper
//        ElasticsearchTransport transport = new RestClientTransport(
//                restClient, new JacksonJsonpMapper());
//        System.out.println(2);
//        // And create the API client
//        ElasticsearchClient esClient = new ElasticsearchClient(transport);
//        System.out.println(3);
////        System.out.println(esClient.ping());
////        esClient.indices().create(c -> c
////                .index("posts")
////        );
//        System.out.println(4);
//        Post post = postRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
//        System.out.println(5);
//        try {
//            IndexResponse response = esClient.index(i -> i
//                    .index("posts")
//                    .id(String.valueOf(post.getId()))
//                    .document(post)
//            );
//            System.out.println(response);
//            log.info("Indexed with version " + response.version());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println(7);
//        GetResponse<Post> response2 = esClient.get(g -> g
//                        .index("posts")
//                        .id(String.valueOf(post.getId())),
//                Post.class
//        );
//        System.out.println(response2);
//        if (response2.found()) {
//            Post post2 = response2.source();
//            log.info("Post name " + post2.getId());
//        } else {
//            log.info ("Post not found");
//        }
//
//    }
//}