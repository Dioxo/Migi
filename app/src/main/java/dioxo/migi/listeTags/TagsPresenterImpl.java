package dioxo.migi.listeTags;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import dioxo.migi.libs.EventBus;
import dioxo.migi.libs.GreenRobotEventBus;

class TagsPresenterImpl implements TagsPresenter {
    TagsView view;
    TagsRepository  repository;
    EventBus eventBus;

    public TagsPresenterImpl(TagsView view) {
        this.view = view;
        repository = new TagsRepositoryImpl();
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


    @Subscribe
    @Override
    public void onEventMainThread(TagsEvent event) {
        switch (event.getEventType()){
            case TagsEvent.SUCESS:
                confirmerTaille(event.getTags());
                break;

            case TagsEvent.ERROR:
                break;
        }
    }

    private void confirmerTaille(ArrayList<String> tags) {
        if(view != null) {
            if (tags.size() > 0) {
                view.afficherTags(tags);
            }else{
                view.afficherTagsVide();
            }
        }
    }

    @Override
    public void chercherTags() {
        repository.chercherTags();
    }

}
