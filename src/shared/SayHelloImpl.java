package shared;

public class SayHelloImpl implements ISayHello {
    @Override
    public String sayHello(String interFaceName,String methodeName) {
        return "I'm server : hello";
    }

    @Override
    public String test() {
        return null;
    }

}