package com.ritvik.thymeleafproject.controllers;

import com.ritvik.thymeleafproject.entities.DoctorEntity;
import com.ritvik.thymeleafproject.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @GetMapping("/")
    public String getAllDoctorsPage(Model model){
        model.addAttribute("doctors", doctorRepository.findAll());
        return "doctors";
    }
    @GetMapping("/add")
    public String addDoctorPage(Model model){
        DoctorEntity doctor = new DoctorEntity();
        model.addAttribute("doctor", doctor);
        return "add";
    }

    @GetMapping("/update/{id}")
    public String updateDoctorPage(@PathVariable Integer id,Model model){
        Optional<DoctorEntity> doctor = doctorRepository.findById(id);
        model.addAttribute("doctor",doctor.get());
        return "update";
    }
    @PostMapping("/add")
    public String addDoctor(@ModelAttribute("doctor") DoctorEntity doctor){
        doctorRepository.save(doctor);
        return "redirect:/";
    }
    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable Integer id,@ModelAttribute("doctor") DoctorEntity doctor){
        DoctorEntity doctorEntity = doctorRepository.findById(id).get();
        doctorEntity.setHospital(doctor.getHospital());
        doctorEntity.setMobile(doctor.getMobile());
        doctorEntity.setName(doctor.getName());
        doctorRepository.save(doctorEntity);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Integer id){
        doctorRepository.deleteById(id);
        return "redirect:/";
    }
}
