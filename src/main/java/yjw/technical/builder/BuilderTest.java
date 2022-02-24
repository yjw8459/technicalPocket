package yjw.technical.builder;

public class BuilderTest {
    public static void main(String[] args) {
        Hero hero = Hero.builder().build();
        Villain villain = Villain.builder().build();
    }
}
