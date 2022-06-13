package com.etnetera.hr.service;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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
        System.out.println("Framework: " + framework.getName() + " with version: " + version + " was created");
        return framework;
    }

    public Iterable<JavaScriptFramework> getFrameworks() {
        return repository.findAll();
    }

    @Transactional
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
                framework.getVersions().add(version);
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
