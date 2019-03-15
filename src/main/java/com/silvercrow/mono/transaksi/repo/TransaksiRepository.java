package com.silvercrow.mono.transaksi.repo;

import com.silvercrow.mono.transaksi.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {
    Optional<Transaksi> findById(Integer id);
}
