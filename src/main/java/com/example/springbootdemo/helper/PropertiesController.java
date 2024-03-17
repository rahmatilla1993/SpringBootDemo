package com.example.springbootdemo.helper;

import com.example.springbootdemo.properties.DataBase;
import com.example.springbootdemo.properties.PersonProperties;
import com.example.springbootdemo.properties.PersonPropertiesWithConf;
import com.example.springbootdemo.yaml.YamlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/property")
public class PropertiesController {

    private final DataBase dataBase;
    private final PersonProperties personProperties;
    private final PersonPropertiesWithConf properties;
    private final YamlConfig yamlConfig;

    @Autowired
    public PropertiesController(DataBase dataBase,
                                PersonProperties personProperties,
                                @Qualifier("personPropConf") PersonPropertiesWithConf properties,
                                YamlConfig yamlConfig) {
        this.dataBase = dataBase;
        this.personProperties = personProperties;
        this.properties = properties;
        this.yamlConfig = yamlConfig;
    }

    @GetMapping("/database")
    public HttpEntity<DataBase> dataBase() {
        return ResponseEntity.ok(dataBase);
    }

    @GetMapping("/person/prop")
    public HttpEntity<PersonProperties> personProperty() {
        return ResponseEntity.ok(personProperties);
    }

    @GetMapping("/person")
    public HttpEntity<Person> person() {
        return ResponseEntity.ok(properties.getPerson());
    }

    @GetMapping("/person/yaml")
    public HttpEntity<Person> personyaml() {
        return ResponseEntity.ok(yamlConfig.getPerson());
    }

    @GetMapping("/env")
    public HttpEntity<Env> getEnv() {
        return ResponseEntity.ok(yamlConfig.getConfig());
    }
}
