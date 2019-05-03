package com.ph.idisciple.idiscipleapp.ui.mainappscreen;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ph.idisciple.idiscipleapp.data.local.model.AboutContent;
import com.ph.idisciple.idiscipleapp.data.local.model.Country;
import com.ph.idisciple.idiscipleapp.data.local.model.FamilyGroup;
import com.ph.idisciple.idiscipleapp.data.local.model.Profile;
import com.ph.idisciple.idiscipleapp.data.local.model.ProfileObject;
import com.ph.idisciple.idiscipleapp.data.local.model.Resource;
import com.ph.idisciple.idiscipleapp.data.local.model.Schedule;
import com.ph.idisciple.idiscipleapp.data.local.model.Speaker;
import com.ph.idisciple.idiscipleapp.data.local.model.Workshop;
import com.ph.idisciple.idiscipleapp.data.local.repository.About.AboutContentRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.Attendees.AttendeesRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.Country.CountryRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.FamilyGroups.FamilyGroupRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.IProfileRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.Resources.ResourcesRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.Schedule.ScheduleRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.Speaker.SpeakerRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.impl.ProfileRepository;
import com.ph.idisciple.idiscipleapp.data.local.repository.workshop.WorkshopRepository;
import com.ph.idisciple.idiscipleapp.data.remote.RestClient;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.BaseApi;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.ListWrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.base.Wrapper;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.ContentDetails;
import com.ph.idisciple.idiscipleapp.data.remote.model.response.ContentResponseWrapper;
import com.ph.idisciple.idiscipleapp.data.remote.service.ContentService;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.communityfragment.RefreshAttendeesEvent;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.morefragment.resourcestab.RefreshResourcesEvent;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.schedulefragment.RefreshScheduleListEvent;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.speakerfragment.RefreshSpeakerListEvent;
import com.ph.idisciple.idiscipleapp.ui.mainappscreen.workshopfragment.RefreshWorkshopListEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

public class MainAppScreenPresenter implements MainAppScreenContract.Presenter {

    private ContentService mContentService;
    private MainAppScreenContract.View mView;
    private ProfileRepository mProfileRepository;

    public AttendeesRepository mAttendeesRepository;
    public ScheduleRepository mScheduleRepository;
    public SpeakerRepository mSpeakerRepository;
    public WorkshopRepository mWorkshopRepository;
    public FamilyGroupRepository mFamilyGroupRepository;
    public CountryRepository mCountryRepository;

    public ResourcesRepository mResourcesRepository;
    public AboutContentRepository mAboutContentRepository;
    private String mUserId;
    private String mUserAvatar;

    // [5/3/2019] Comment Favorite for now
//    public SavedProfileFavoritesRepository mSavedProfileFavoritesRepository;

    public MainAppScreenPresenter(MainAppScreenContract.View view) {
        mView = view;
        mContentService = RestClient.getInstance().getContentService();
        mProfileRepository = new ProfileRepository();

        mAttendeesRepository = new AttendeesRepository();
        mScheduleRepository = new ScheduleRepository();
        mSpeakerRepository = new SpeakerRepository();
        mWorkshopRepository = new WorkshopRepository();
        mFamilyGroupRepository = new FamilyGroupRepository();
        mCountryRepository = new CountryRepository();
        mResourcesRepository = new ResourcesRepository();
        mAboutContentRepository = new AboutContentRepository();

        // [5/3/2019] Comment Favorite for now
//        mSavedProfileFavoritesRepository = new SavedProfileFavoritesRepository();
    }

    @Override
    public void fetchData(){
        mProfileRepository.getKeyItem(ProfileObject.ProfileType.USER_ID, new IProfileRepository.onGetKeyItemCallback() {
            @Override
            public void onSuccess(ProfileObject keySettingItem) {
                mUserId = keySettingItem.getItemValue();
                mView.setUserId(mUserId);
                fetchData(mUserId);
            }
        });
    }

    private void fetchData(String userId) {
        mContentService.getContent(userId).enqueue(new Callback<Wrapper<ContentResponseWrapper>>() {
            @Override
            public void onResponse(Call<Wrapper<ContentResponseWrapper>> call, Response<Wrapper<ContentResponseWrapper>> response) {
                switch (response.code()) {
                    case 200:
                    case 201:
                        ContentResponseWrapper wrapper = response.body().getData();
                        ContentDetails contentProfile = wrapper.getAssetsData().getContentProfile();
                        new JsonTask().execute(contentProfile.getJsonPathFile(), "0");
                        ContentDetails contentSchedule = wrapper.getAssetsData().getContentSchedule();
                        new JsonTask().execute(contentSchedule.getJsonPathFile(), "1");
                        ContentDetails contentSpeakers = wrapper.getAssetsData().getContentSpeakers();
                        new JsonTask().execute(contentSpeakers.getJsonPathFile(), "2");
                        ContentDetails contentWorkshops = wrapper.getAssetsData().getContentWorkshops();
                        new JsonTask().execute(contentWorkshops.getJsonPathFile(), "3");
                        ContentDetails contentFamilyGroup = wrapper.getAssetsData().getContentFamilyGroup();
                        new JsonTask().execute(contentFamilyGroup.getJsonPathFile(), "4");
                        ContentDetails contentCountry = wrapper.getAssetsData().getContentCountry();
                        new JsonTask().execute(contentCountry.getJsonPathFile(), "5");
                        ContentDetails contentResources = wrapper.getAssetsData().getContentResources();
                        new JsonTask().execute(contentResources.getJsonPathFile(), "6");
                        ContentDetails contentAboutContent = wrapper.getAssetsData().getContentAboutContent();
                        new JsonTask().execute(contentAboutContent.getJsonPathFile(), "7");
                        break;
                    case 422:
                        Gson gson = new Gson();
                        try {

                            BaseApi apiErrorResponse = gson.fromJson(response.errorBody().string(), BaseApi.class);
                            mView.onFetchDataFailed(apiErrorResponse.getMessage());

                        } catch (IOException e) {
                            e.printStackTrace();
                            mView.showGenericError();
                        }
                        break;
                    default:
                        mView.showGenericError();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Wrapper<ContentResponseWrapper>> call, Throwable t) {
                mView.prepareBundleToPassInPrepForViewOwnProfile();
                mView.onFetchDataSuccess();
//                if (t.getCause() != null && t.getCause().getMessage().contains("ENETUNREACH (Network is unreachable)"))
//                    mView.showNoInternetConnection();
//                else if ((t.getCause() != null && t.getCause().getMessage().contains("ETIMEDOUT (Connection timed out)")) || t.getMessage().contains("failed to connect"))
//                    mView.showTimeoutError();
//                else
//                     mView.showGenericError();
            }
        });
    }

    /**
     * Get Json File from path_file, and save locally fetched data
     */
    private class JsonTask extends AsyncTask<String, String, String> {
        String type = "-1";

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;


            try {
                type = params[1];
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    //Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Remove \n and make it as object not Array
            if (result.startsWith("\uFEFF")) {
                result = result.substring(1);
            }

            result = result.substring(1, result.length() - 2).replace("\n","");
            // save json items
            Gson jsonReturned =  new Gson();
            switch (type){
                case "0":
                    mAttendeesRepository.resetStorage();
                    Type typeProfileWrapper = new TypeToken<ListWrapper<Profile>>() {}.getType();
                    ListWrapper<Profile> wrapperProfile = jsonReturned.fromJson(result, typeProfileWrapper);
                    List<Profile> jsonProfile = wrapperProfile.getData();
                    mAttendeesRepository.addItemList(jsonProfile);

                    // [5/3/2019] Comment Favorite for now
//                    if(mSavedProfileFavoritesRepository.size() == 0) mSavedProfileFavoritesRepository.addItemList(jsonProfile);

                    Profile ownProfile = from(jsonProfile).where("getId", eq(mUserId)).first();
                    mView.setProfileAvatar(ownProfile.getUserImageUrl(), ownProfile.getUserCountry());

                    EventBus.getDefault().post(new RefreshAttendeesEvent());
                    break;
                case "1":
                    mScheduleRepository.resetStorage();
                    Type typeScheduleWrapper = new TypeToken<ListWrapper<Schedule>>() {}.getType();
                    ListWrapper<Schedule> wrapperSchedule = jsonReturned.fromJson(result, typeScheduleWrapper);
                    List<Schedule> jsonSchedule = wrapperSchedule.getData();
                    mScheduleRepository.addItemList(jsonSchedule);
                    EventBus.getDefault().post(new RefreshScheduleListEvent());
                    break;
                case "2":
                    mSpeakerRepository.resetStorage();
                    Type typeSpeakerWrapper = new TypeToken<ListWrapper<Speaker>>() {}.getType();
                    ListWrapper<Speaker> wrapperSpeaker = jsonReturned.fromJson(result, typeSpeakerWrapper);
                    List<Speaker> jsonSpeaker = wrapperSpeaker.getData();
                    mSpeakerRepository.addItemList(jsonSpeaker);
                    EventBus.getDefault().post(new RefreshSpeakerListEvent());
                    break;
                case "3":
                    mWorkshopRepository.resetStorage();
                    Type typeWorkshopWrapper = new TypeToken<ListWrapper<Workshop>>() {}.getType();
                    ListWrapper<Workshop> wrapperWorkshop = jsonReturned.fromJson(result, typeWorkshopWrapper);
                    List<Workshop> jsonWorkshop = wrapperWorkshop.getData();
                    mWorkshopRepository.addItemList(jsonWorkshop);
                    EventBus.getDefault().post(new RefreshWorkshopListEvent());
                    break;
                case "4":
                    mFamilyGroupRepository.resetStorage();
                    Type typeFamilyGroupWrapper = new TypeToken<ListWrapper<FamilyGroup>>() {}.getType();
                    ListWrapper<FamilyGroup> wrapperFamilyGroup = jsonReturned.fromJson(result, typeFamilyGroupWrapper);
                    List<FamilyGroup> jsonFamilyGroup = wrapperFamilyGroup.getData();
                    mFamilyGroupRepository.addItemList(jsonFamilyGroup);
                    break;
                case "5":
                    Type typeCountryWrapper = new TypeToken<ListWrapper<Country>>() {}.getType();
                    ListWrapper<Country> wrapperCountry = jsonReturned.fromJson(result, typeCountryWrapper);
                    List<Country> jsonCountry = wrapperCountry.getData();
                    mCountryRepository.addItemList(jsonCountry);
                    break;
                case "6":
                    mResourcesRepository.resetStorage();
                    Type typeResourcesWrapper = new TypeToken<ListWrapper<Resource>>() {}.getType();
                    ListWrapper<Resource> wrapperResources = jsonReturned.fromJson(result, typeResourcesWrapper);
                    List<Resource> jsonResources = wrapperResources.getData();
                    mResourcesRepository.addItemList(jsonResources);
                    EventBus.getDefault().post(new RefreshResourcesEvent());
                    break;
                case "7":
                    mAboutContentRepository.resetStorage();
                    Type typeAboutContentWrapper = new TypeToken<ListWrapper<AboutContent>>() {}.getType();
                    ListWrapper<AboutContent> wrapperAboutContent = jsonReturned.fromJson(result, typeAboutContentWrapper);
                    List<AboutContent> jsonAboutContent = wrapperAboutContent.getData();
                    mAboutContentRepository.addItemList(jsonAboutContent);
                    mView.prepareBundleToPassInPrepForViewOwnProfile();
                    mView.onFetchDataSuccess();
                    break;
            }

        }
    }


}
