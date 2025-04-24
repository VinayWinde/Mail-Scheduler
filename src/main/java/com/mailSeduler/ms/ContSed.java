package com.mailSeduler.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContSed {

    @Autowired
    private ServiceFill serviceFill;


    //  /api/add
    @PostMapping("/add")
    public ResponseEntity<Form> addCont(@RequestBody Form formList){
        Form sav=serviceFill.addDetails(formList);
        return new ResponseEntity<>(sav,HttpStatus.OK);


    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Form> updateForm(@PathVariable String id, @RequestBody Form updatedForm) {
        Form upf=serviceFill.update(id,updatedForm);
        return new ResponseEntity<>(upf, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public String del(@PathVariable String id){
        serviceFill.delsev(id);
        return "DELETED";
    }




}
