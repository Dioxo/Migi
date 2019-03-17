package dioxo.migi.listeTags;

public interface TagsPresenter {
    void onCreate();
    void onDestroy();

    void onEventMainThread(TagsEvent event);

    void chercherTags();
    void chercherNotesAssociates(String tag);

}
