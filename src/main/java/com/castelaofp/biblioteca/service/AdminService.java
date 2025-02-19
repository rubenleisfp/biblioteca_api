package com.castelaofp.biblioteca.service;

import com.castelaofp.biblioteca.dto.AdminDto;
import com.castelaofp.biblioteca.dto.LibroDto;
import com.castelaofp.biblioteca.mapper.AdminMapper;
import com.castelaofp.biblioteca.mapper.LibroMapper;
import com.castelaofp.biblioteca.model.Admin;
import com.castelaofp.biblioteca.model.Libro;
import com.castelaofp.biblioteca.repository.AdminRepository;
import com.castelaofp.biblioteca.repository.EjemplarRepository;
import com.castelaofp.biblioteca.repository.LibroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private static Logger LOG = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;


    public List<AdminDto> findAll() {
        return AdminMapper.toDto(adminRepository.findAll());
    }

    public Optional<Admin> findByEmailAndPassword(AdminDto adminDto) {
        Admin byUsernameAndPassword = adminRepository.findByEmailAndPassword(adminDto.getEmail(), adminDto.getPassword());
        return Optional.ofNullable(byUsernameAndPassword);
    }

}
