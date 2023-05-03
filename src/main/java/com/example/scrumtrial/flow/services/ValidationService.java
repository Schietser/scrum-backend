package com.example.scrumtrial.flow.services;

import com.example.scrumtrial.flow.mappers.ValidationMapper;
import com.example.scrumtrial.flow.exceptions.UserNotValidException;
import com.example.scrumtrial.models.dtos.ValidationRequest;
import com.example.scrumtrial.models.dtos.ValidationResponse;
import com.example.scrumtrial.models.entities.ValidationEntity;
import com.example.scrumtrial.models.repositories.ValidationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValidationService {

    private final ValidationRepository vr;
    private final ValidationMapper vm;

    public ValidationService(ValidationRepository vr, ValidationMapper vm) {
        this.vr = vr;
        this.vm = vm;
    }

    public Optional<ValidationResponse> find(ValidationRequest req) throws UserNotValidException{
        Optional<ValidationEntity> foundValidation = this.vr.findByIdentifierAndCode(req.getIdentifier(), req.getCode());
        return foundValidation.map(this.vm::toResponse);
    }

    public List<ValidationResponse> findAll(){
        return this.vr.findAll().stream().map(vm::toResponse).collect(Collectors.toList());
    }
}
