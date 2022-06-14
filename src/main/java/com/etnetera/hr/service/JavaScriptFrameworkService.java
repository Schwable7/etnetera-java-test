package com.etnetera.hr.service;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class JavaScriptFrameworkService {


    private final JavaScriptFrameworkRepository repository;

    @Autowired
    public JavaScriptFrameworkService(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }


    public JavaScriptFramework createJavaScriptFramework(String name, String version, BigDecimal hypeLevel, Date deprecationDate) {
        final JavaScriptFramework foundFramework = repository.findJavaScriptFrameworkByName(name);
        if (foundFramework != null) {
            System.err.println("Framework with name " + name + " already exists!");
            return null;
        }

        JavaScriptFramework framework = new JavaScriptFramework();
        framework.setName(name);
        framework.setVersions(Arrays.asList(version));
        framework.setHypeLevel(hypeLevel);
        framework.setDeprecationDate(deprecationDate);
        repository.save(framework);
        System.out.printf("Framework: %s with version: %s was created%n", framework.getName(), version);
        return framework;
    }

    public Iterable<JavaScriptFramework> getFrameworks() {
        return repository.findAll();
    }

    public JavaScriptFramework updateFramework(String name, String version, BigDecimal hypeLevel, Date deprecationDate) {
        final JavaScriptFramework framework = repository.findJavaScriptFrameworkByName(name);

        if (framework == null) {
            System.err.println("Framework with name: " + name + " was not found");
            return null;
        }
        else {
            int changes = 0;
            if (name != null && !Objects.equals(name, framework.getName())) {
                framework.setName(name);
                changes++;
            }
            if (version != null && !framework.getVersions().contains(version)) {
                final List<String> versions = framework.getVersions();
                final List<String> updatetVersions = new ArrayList<>(versions);
                updatetVersions.add(version);
                framework.setVersions(updatetVersions);
                changes++;
            }
            if (hypeLevel != null && !Objects.equals(hypeLevel, framework.getHypeLevel())) {
                framework.setHypeLevel(hypeLevel);
                changes++;
            }
            if (deprecationDate != null && !Objects.equals(deprecationDate, framework.getDeprecationDate())) {
                framework.setDeprecationDate(deprecationDate);
                changes++;
            }

            if (changes > 0) {
                repository.save(framework);
                System.out.println("Framework: " + name + " was updated");
            }
            return framework;
        }

    }

    public void deleteFramework(String name) {
        final JavaScriptFramework framework = repository.findJavaScriptFrameworkByName(name);
        if (framework == null) {
            System.err.println("Framework: " + name + " was not found");
        } else {
            repository.delete(framework);
            System.out.println("Framework: " + name + " was deleted");
        }
    }
}
