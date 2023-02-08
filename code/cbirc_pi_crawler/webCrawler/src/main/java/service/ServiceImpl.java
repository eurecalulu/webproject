package service;
import javax.jws.WebService;


@WebService
public class ServiceImpl implements ServiceInterface {
    @Override
    public String getData() {
        return "Doc Data";
    }
}
