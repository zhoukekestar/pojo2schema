package org.example;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.Option;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


//@JsonProperty(namespace = "title", value = "aaa")
public class Pojo {

    public String a = "a";

    public ColumnName b;

    public static enum ColumnName {


        EVENT_ID,

        TIMESTMP
    }


    public List<OfferSupplyConfigRule> ruleSets;


    public TestClassForSubtypeResolution testClassForSubtypeResolution;

    public static class TestClassForSubtypeResolution {

        public boolean b = false;
        @JsonPropertyDescription("A member description")
        public TestSuperClass supertypeA;
        public TestSuperClass supertypeB;

        TestClassForSubtypeResolution() {
            this.supertypeA = new TestSubClassA();
            this.supertypeB = new TestSubClassB();
        }
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = TestSubClassA.class, name = "SubClassA"),
            @JsonSubTypes.Type(value = TestSubClassB.class, name = "SubClassB")
    })
    public static class TestSuperClass {

    }

    public static class TestSubClassA extends TestSuperClass {

        public String aProperty;

        public TestSubClassA() {
            this.aProperty = "a";
        }
    }

    public static class TestSubClassB extends TestSuperClass {

        public int bProperty;

        public String c = "c";

        public String d = "d";

        TestSubClassB() {
            this.bProperty = 'b';
        }
    }


    public class Rule1 implements  OfferSupplyConfigRule {
        public String name = "rule1";

        public String testa = "testa";
    }

    public class Rule2 implements  OfferSupplyConfigRule {
        public String name = "rule1";

        public String testb = "testb";

        public String testc = "testc";

        public boolean a = false;


        public List<Rule1> rule1s;

    }


    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Rule1.class, name = "Rule1"),
            @JsonSubTypes.Type(value = Rule2.class, name = "Rule2")
    })
    public static interface OfferSupplyConfigRule {
        public String name = null;
    }

}
