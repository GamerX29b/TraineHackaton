package com.example.TraineeHackathon.controllers;

import com.example.TraineeHackathon.Classes.JsonResponse;
import com.example.TraineeHackathon.Classes.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PersonController {

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> person (
            @RequestParam(value = "json") String json
    ) {
        ModelAndView mv = new ModelAndView();

        System.out.println(json);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    /*{
        JSONObject jsonObject = null;



        Long id;
        String name ="";
        Date birthdate;
        if(jsonObject.has("name")) {
            name = jsonObject.getString("name");
        }


    }

    public @ResponseBody mvc addNewPerson(@RequestBody mvc jsonString){

        return mvc
    }*/
    @ResponseBody
    public JsonResponse addPerson(@RequestBody Person person){
        return new JsonResponse("OK","");
    }
}
