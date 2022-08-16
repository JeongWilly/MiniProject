package sparta.miniproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long comment_id;

    @Column(nullable = false)
    private String nickname;

    @Column(name="c_content",nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean isremoved = false;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    @JsonBackReference
//    private parent;




}
