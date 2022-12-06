package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    // 테이블에서 FK인 MEMBER_ID 가 ORDERS 테이블에 있으므로 얘가 연관관계의 주인이다.

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long id;

    // 나를 주문하는 회원은 하나!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 비즈니스적으로 상당히 의미가 있다.
    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems = new ArrayList<>();

    // 지금은 테이블에 컬럼 이름 그대로 orderDate 이렇게 되는데 스프링부트로 하면 order_date 로 저장해버림. (자바의 카멜표기법 -> 뱀 표기법)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    // 양방향 연관관계...
    // 하는 이유 : querydsl 이나 jpql 을  복잡하게 짜기 위해서;
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
