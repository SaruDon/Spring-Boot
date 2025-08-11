package com.sarvesh.project.uber.uber.entities;

import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,optional = false,cascade = CascadeType.DETACH)
    private User user;

    private Double balance;

    @OneToMany(mappedBy = "wallet" ,fetch = FetchType.LAZY)
    private List<WalletTransaction> transactionList;


}
