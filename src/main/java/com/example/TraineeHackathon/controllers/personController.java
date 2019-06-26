package com.example.TraineeHackathon.controllers;

import com.example.TraineeHackathon.Classes.JsonResponse;
import com.example.TraineeHackathon.Classes.Person;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/central")
public class personController {

    @RequestMapping(value = "/person", method = RequestMethod.POST,
            consumes="application/json",produces="application/json")
    public @ResponseBody String getPerson(HttpServletRequest req, HttpServletResponse res){
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(req.getParameter("jsonObject"));
        }catch (JSONException _instance){System.out.println("LOL");
        }


        Long id;
        String name ="";
        Date birthdate;
        if(jsonObject.has("name")) {
            name = jsonObject.getString("name");
        }


    }

   /* public @ResponseBody mvc addNewPerson(@RequestBody mvc jsonString){

        return mvc
    }*/
    @ResponseBody
    public JsonResponse addPerson(@RequestBody Person person){
        return new JsonResponse("OK","");
    }
}
