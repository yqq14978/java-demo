package designPatterns;

/**
 * Created with IDEA
 *
 * @author:yeqq
 * @Date:2020/5/8
 * @Time:16:14
 */
public class Builder {

    public static void main(String[] args) {
        Computer computer1 = new Computer.Builder(new Cpu() , "联想电脑").setMouse(new Mouse()).build();
        Director director = new Director();
        ComputerBuider buider = new MacComputerBuilder(new Cpu() , "苹果电脑");
        director.buildMouseComputer(buider);
        Computer computer2 = buider.build();
    }

}

class Computer{

    private String name;
    private Cpu cpu;
    private Mouse mouse;
    private Keyboard keyboard;
    private GraphicsCard graphicsCard;
    private Monitor monitor;
    public Computer (Cpu cpu , String name){
        this.name = name;
        this.cpu = cpu;
    }

    public static class Builder{

        private Computer computer;

        public Builder setMouse(Mouse mouse) {
            computer.mouse = mouse;
            return this;
        }

        public Builder setKeyboard(Keyboard keyboard) {
            computer.keyboard = keyboard;
            return this;
        }

        public Builder setGraphicsCard(GraphicsCard graphicsCard) {
            computer.graphicsCard = graphicsCard;
            return this;
        }

        public Builder setMonitor(Monitor monitor) {
            computer.monitor = monitor;
            return this;
        }

        public Builder (Cpu cpu , String name){
            this.computer = new Computer(cpu , name);
        }

        public Computer build(){
            System.out.println(computer.name + "创建完毕");
            return computer;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
}

interface ComputerBuider{
    ComputerBuider setCpu(Cpu cpu);
    ComputerBuider setMouse(Mouse mouse);
    ComputerBuider setKeyboard(Keyboard keyboard);
    ComputerBuider setGraphicsCard(GraphicsCard graphicsCard);
    ComputerBuider setMonitor(Monitor monitor);
    Computer build();
}

class MacComputerBuilder implements ComputerBuider {

    private Computer computer;

    public MacComputerBuilder (Cpu cpu , String name){
        this.computer = new Computer(cpu , name);
    }

    @Override
    public ComputerBuider setCpu(Cpu cpu) {
        computer.setCpu(cpu);
        return this;
    }

    @Override
    public ComputerBuider setMouse(Mouse mouse) {
        computer.setMouse(mouse);
        return this;
    }

    @Override
    public ComputerBuider setKeyboard(Keyboard keyboard) {
        computer.setKeyboard(keyboard);
        return this;
    }

    @Override
    public ComputerBuider setGraphicsCard(GraphicsCard graphicsCard) {
        computer.setGraphicsCard(graphicsCard);
        return this;
    }

    @Override
    public ComputerBuider setMonitor(Monitor monitor) {
        computer.setMonitor(monitor);
        return this;
    }

    @Override
    public Computer build() {
        System.out.println(computer.getName() + "创建完成");
        return computer;
    }
}

class Director{
    public void buildMouseComputer(ComputerBuider buider){
        buider.setMouse(new Mouse());
    }
}

class Cpu{}

class Mouse{}

class Keyboard{}

class GraphicsCard{}

class Monitor{}
