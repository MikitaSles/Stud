package entity;

class Material {
    private String name;
    private double density;

    public Material() {
    }

    public Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public String getName() {
        return name;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return name + ";" + density;
    }
}

class Subject {
    private String name;
    private Material material;
    private double volume;

    public Subject() {
    }

    public Subject(String name, Material material, double volume) {
        this.name = name;
        this.material = material;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getVolume() {
        return volume;
    }

    public double getMass() {
        return material.getDensity() * volume;
    }

    @Override
    public String toString() {
        return name + ";" + material + ";" + volume + ";" + getMass();
    }
}

public class Runner {
    public static void main(String[] args) {
        Material steel = new Material("steel", 7850.0);
        Subject wire = new Subject("wire", steel, 0.03);

        System.out.println(wire.toString());

        Material copper = new Material("copper", 8500.0);
        wire.setMaterial(copper);

        System.out.println("Масса проволоки: " + wire.getMass() + " кг.");
    }
}
