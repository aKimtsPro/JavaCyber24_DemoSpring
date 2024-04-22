package be.tftic.java.spring.bll;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoService {

    private final List<String> names = new ArrayList<>();

    public void add(String name){
        names.add(name);
    }

    public String getOne(int index){
        return names.get(index);
    }

    public List<String> getAll(){
        return new ArrayList<>(names);
    }

    public void remove(int index){
        names.remove(index);
    }

}
