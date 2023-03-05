package com.capa.jwtAuthThoughts.goldenThoughts.services;

import com.capa.jwtAuthThoughts.goldenThoughts.controllers.InsertGoldenThoughtRequest;
import com.capa.jwtAuthThoughts.goldenThoughts.entity.GoldenThought;
import com.capa.jwtAuthThoughts.goldenThoughts.repository.GoldenThoughtsRepository;
import com.capa.jwtAuthThoughts.goldenThoughts.utlis.GoldenThoughtResponse;
import com.capa.jwtAuthThoughts.user.User;
import com.capa.jwtAuthThoughts.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoldenThoughtsService {
    private final GoldenThoughtsRepository goldenThoughtsRepository;

        public List<GoldenThoughtResponse> findAll() {
            List<GoldenThought> goldenThoughts = goldenThoughtsRepository.findAll();
            List<GoldenThoughtResponse> goldenThoughtResponses = new ArrayList<>();
            goldenThoughts.forEach(thought -> {
                goldenThoughtResponses.add(new GoldenThoughtResponse(thought.getId(), thought.getValue(), thought.isDone(),
                        new UserDTO(thought.getUser().getId(),thought.getUser().getFirstname(),thought.getUser().getLastname())));
            });

            return goldenThoughtResponses;
        }

    public String save(InsertGoldenThoughtRequest insertGoldenThoughtRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(insertGoldenThoughtRequest.getMessage().length()>0) {
                GoldenThought goldenThought = GoldenThought.builder()
                        .value(insertGoldenThoughtRequest.getMessage())
                        .isDone(false)
                        .user(user)
                        .build();

                goldenThoughtsRepository.save(goldenThought);

                return "thought succesfully saved in db!";
            }else return "empty thought";

    }

    public GoldenThoughtResponse getById(Integer id) {
            Optional<GoldenThought> goldenThought = goldenThoughtsRepository.findById(id);
            if (goldenThought.isPresent()){
                GoldenThought value = goldenThought.get();
                return new GoldenThoughtResponse(value.getId(),value.getValue(),value.isDone(),new UserDTO(value.getUser().getId(),value.getUser().getFirstname(),value.getUser().getLastname()));
            }else return new GoldenThoughtResponse();
    }

    public List<GoldenThoughtResponse> findUnDone() {
        List<GoldenThoughtResponse> goldenThoughtsRespones = findAll();

        return goldenThoughtsRespones
                .stream()
                .filter(t -> !t.isDone())
                .collect(Collectors.toList());
    }

    public String setThoughtDone(Integer id) {
            Optional<GoldenThought> goldenThought = goldenThoughtsRepository.findById(id);
            AtomicReference<String> returnMessage = new AtomicReference<String>();

            goldenThought.ifPresentOrElse((g) -> {
                g.setDone(true);

                goldenThoughtsRepository.save(g);
                returnMessage.set("now " + g.getValue() + " is done !");
            },() -> {
                returnMessage.set("can not find task you want to set done");
            });

            return returnMessage.get();
    }
}
