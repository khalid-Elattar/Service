package com.example.BackEndService.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "feedback")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @PositiveOrZero
    private Double Rating;
    private String Comment;

    @OneToOne(mappedBy = "feedBack")
    private Appointment appointment;

}
