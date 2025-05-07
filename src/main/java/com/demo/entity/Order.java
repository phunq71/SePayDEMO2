package com.demo.entity;



import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="orders")
public class Order implements Serializable{
	@Id
    @Column(name = "id", length = 50)
    private String id;

    @Column(name = "StatusTT")
    private boolean statusTT;
	
}
