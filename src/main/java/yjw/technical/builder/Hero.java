package yjw.technical.builder;


public class Hero {
    private String name;

    private int power;

    private int age;

    private Hero(Builder builder){
        this.name = builder.name;
        this.power = builder.power;
        this.age = builder.age;
    }

    public static Builder builder(){
        return new Builder();
    }

    static class Builder{
        private String name;
        private int power;
        private int age;

        public Builder(){
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder power(int power){
            this.power = power;
            return this;
        }

        public Builder age(int age){
            this.age = age;
            return this;
        }

        public Hero build(){
            return new Hero(this);
        }
    }
}
