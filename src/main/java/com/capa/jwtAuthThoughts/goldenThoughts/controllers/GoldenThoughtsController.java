package com.capa.jwtAuthThoughts.goldenThoughts.controllers;

import com.capa.jwtAuthThoughts.goldenThoughts.entity.GoldenThought;
import com.capa.jwtAuthThoughts.goldenThoughts.services.GoldenThoughtsService;
import com.capa.jwtAuthThoughts.goldenThoughts.utlis.GoldenThoughtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/thoughts-controller")
@RequiredArgsConstructor
public class GoldenThoughtsController {

    private final GoldenThoughtsService goldenThoughtsService;

    @GetMapping("/getAllThoughts")
    public ResponseEntity<List<GoldenThoughtResponse>> getAllThoughts(){
        return ResponseEntity.ok(goldenThoughtsService.findAll());
    }


    @GetMapping("/getUnDoneThoughts")
    public ResponseEntity<List<GoldenThoughtResponse>> getUnDoneThoughts(){
        return ResponseEntity.ok(goldenThoughtsService.findUnDone());
    }

    @GetMapping("getThought/{id}")
    public ResponseEntity<GoldenThoughtResponse> getThoughtById(@PathVariable("id") Integer id){
       return ResponseEntity.ok(goldenThoughtsService.getById(id));
    }

    @PostMapping("/newThought")
    public ResponseEntity<String> newThought(@RequestBody InsertGoldenThoughtRequest insertGoldenThoughtRequest){

        return ResponseEntity.ok(goldenThoughtsService.save(insertGoldenThoughtRequest));
    }

    @PutMapping("/setThoughtDone/{id}")
    public ResponseEntity<String> setThoughtDone(@PathVariable("id") Integer id){

        return ResponseEntity.ok(goldenThoughtsService.setThoughtDone(id));
    }
}

