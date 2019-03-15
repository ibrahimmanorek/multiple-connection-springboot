package com.silvercrow.mono.controller;

import com.silvercrow.mono.transaksi.model.Transaksi;
import com.silvercrow.mono.transaksi.repo.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @RequestMapping(value = "transaksi/{id}", method = RequestMethod.GET)
    public Optional<Transaksi> getUser(@PathVariable("id") Integer id){
        return transaksiRepository.findById(id);
    }
}
