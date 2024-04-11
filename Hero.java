import java.util.*;

public class Hero implements Contract{

    String name;
    int energy;
    Number size;
    private ArrayList<String> inventory;
    static Scanner scanner = new Scanner(System.in);

    /* Constructor for hero to establish basic traits
     * @param name: the name of the hero
     */
    public Hero(String name){
        this.name = name;
        this.inventory= new ArrayList<String>();
        this.inventory.add("Sword");
        this.energy = 100;
        this.size = 100;

    }
    /* toString to introduce hero upon creation */
    public String toString() {
        return "The great hero " + this.name + " has awoken from their slumber! They currently possess " + inventory + " and have an energy level of " + energy + ". Their size is " + size + ".";
    }

    /* Method to grab a String item
     * @param item: the item that is to be grabbed 
     */
    public void grab(String item) {
        this.inventory.add(item);
        System.out.println(this.name + " has just grabbed " + item + ". Now, they possess " + inventory + ".");
    }

    /* Method to drop a String item
     * @param item: the item that is to be dropped if currently in inventory
     */
    public String drop(String item){
        if(inventory.contains(item)){
            this.inventory.remove(item);
            System.out.println(this.name + " has dropped " + item + ". Now, they only possess " + inventory + ".");
        } else{
            throw new RuntimeException(this.name + " was not holding " + item + " so they cannot drop it.");
            }
        return item;
        }
    
    /* Method to examine item to learn more about it
     * @param item: the item that can be examined if it is held
     */
    public void examine(String item){
        if(inventory.contains(item)){
            System.out.println( item + " is currently held by " + this.name + ". They take it out and look at it. Wow, what an interesting item!");
        } else {
            System.out.println(this.name + " is not currently holding " + item + ". Would you like to pick it up? (yes/no)");
            String response = scanner.nextLine();
            if (response.equals("yes")){
                grab(item);
                System.out.println(this.name + " examines " + item + ". Yep, it sure is a thing.");
            } else if (response.equals("no")) {
                System.out.println( this.name + " attempts to examine " + item + " from a distance, but it is hard to see. They should've worn their glasses if they weren't going to pick it up.");
            } else{
                throw new RuntimeException("Response must be 'yes' or 'no'.");
            }
        }
    }

    /* Method to use item if held
     * @param item: the item that can be used if held
     */
    public void use(String item){
        if(inventory.contains(item)){
            inventory.remove(item);
            System.out.println(this.name + " has used the item...\nUh oh! It broke! Now, they only possess " + inventory + ".");
        } else {
            throw new RuntimeException(this.name + " cannot use " + item + " because they are not holding it. Grab it first in order to use it.");
        }
        
    }

    /* Method to walk if hero has at least 20 energy
     * @param direction: the direction the hero walks
     * @return false if hero does not have enough energy to walk, true if they do
     */
    public boolean walk(String direction){
        if (energy<=20) {
            System.out.println("Oh no! " + this.name + " doesn't have enough energy to walk. They need to rest first.");
            return false;
        } else {
            energy -= 20;
            System.out.println(this.name + " has walked for 20 minutes in the direction " + direction + ". Their energy level is now " + energy + ".");
            return true;
        }
    }

    /* Method to fly if hero has enough energy
     * @param x: how many feet horizontally they want to fly
     * @param y: how many feet vertically they want to fly
     * @return false if hero does not have enough energy to fly, true if they do
     */
    public boolean fly(int x, int y){
        int energyRequired;
        if(x*y > 100) { //this is because the max energy level is 100, so flying cannot require more than 100 energy
            energyRequired = 100;
        } else {
            energyRequired = x*y;
        }
        if (energy<=energyRequired) {
            System.out.println("Oh no! " + this.name + " doesn't have enough energy to fly. They need to rest first.");
            return false;
        } else {
            energy -= energyRequired;
            System.out.println(this.name + " has flown " + x + " feet horizontally and " + y + " feet vertically. Their energy level is now " + energy + ".");
            return true;
        }
    }
    /* Method to shrink size of hero
     * @return size after shrinking
     */
    public Number shrink(){
        Number newSize;
        if (size.equals(20)) {
            throw new RuntimeException(this.name + " is already tiny! If they shrink any more, they would cease to exist!");
        } else if (size.equals(500)) {
            newSize = 100;
        } else {
            newSize = 20;
        }
        System.out.println(this.name + " was of size " + size + " but they are shrinking! Now, they are only of size " + newSize + ".");
        size = newSize;
        return size;
    }

    /* Method to grow size of hero
     * @return size after growing
     */
    public Number grow(){
        Number newSize;
        if (size.equals(500)) {
            throw new RuntimeException(this.name + " is already massive! If they grow any more, they would be too large for Earth!");
        } else if (size.equals(20)) {
            newSize = 100;
        } else {
            newSize = 100;
        }
        System.out.println(this.name + " was of size " + size + " but they are growing! Now, they are of size " + newSize + ".");
        size = newSize;
        return size;
    }

    /* Method to reset hero's energy level */
    public void rest(){
        energy = 100;
        System.out.println(this.name + " took a nice, long nap. Their energy level is now " + energy + ".");
    }

    /* Method to reset to default state */
    public void undo(){
        System.out.println(this.name + " regrets their previous actions. Assessing damage...");
        energy = 100;
        size = 100;
        inventory.clear();
        inventory.add("Sword");
        System.out.println(this.name + " has been reset to the default state. They now have an energy level of " + energy + ", size of " + size + " and have in their possession " + inventory + ".");
    }

public static void main(String[] args) {
    Hero myHero = new Hero("Link");
    System.out.println(myHero);
    myHero.grab("chicken");
    myHero.examine("chicken");
    myHero.use("chicken");
    myHero.grab("rock");
    myHero.drop("rock");
    myHero.shrink();
    myHero.grow();
    myHero.grow();
    myHero.fly(5, 5);
    myHero.walk("west");
    myHero.undo();
    }
}



