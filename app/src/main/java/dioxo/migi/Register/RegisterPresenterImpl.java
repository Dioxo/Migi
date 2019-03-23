package dioxo.migi.Register;

import org.greenrobot.eventbus.Subscribe;

import dioxo.migi.Objets.Objs.User;
import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView view;
    private RegisterRepository repository;
    private EventBus  eventBus;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        repository = new RegisterRepositoryImpl();
        eventBus = GreenRobotEventBus.getInstance();
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RegisterEvent registerEvent) {
        if(view != null) {
            switch (registerEvent.getEventType()) {
                case RegisterEvent.REGISTER_SUCCESS:
                    view.goToNextPage();
                    break;

                case RegisterEvent.REGISTER_ERROR:
                    view.enableInputs();
                    view.hideProgressBar();
                    view.registerError(registerEvent.getMessage());
                    break;
            }
        }
    }

    @Override
    public void registerUser(User user) {
        if(view != null){
            view.showProgressBar();
            view.disableInputs();
        }
        repository.registerUser(user);
    }
}
