package com.me92100984.guestbook.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name="tbl_guestbook")
public class Guestbook extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long gno;

  @Column(name="title", nullable = false, length = 100)
  private String title;

  @Column(name="content", nullable = false, length = 1500)
  private String content;

  @Column(name="writer", nullable = false, length = 50)
  private String writer;

}
