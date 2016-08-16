package com.austindroids.austinfeedsme.events;

import android.util.Log;

import com.austindroids.austinfeedsme.data.Event;
import com.austindroids.austinfeedsme.data.EventsDataSource;
import com.austindroids.austinfeedsme.data.EventsRepository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by darrankelinske on 5/2/16.
 */
public class EventsPresenter implements EventsContract.UserActionsListener {


    private EventsRepository repository;
    private EventsContract.View view;

    public EventsPresenter(EventsRepository repository, EventsContract.View view) {
        this.view = view;
        this.repository = repository;

    }

    @Override
    public void loadEvents() {
        repository.getEvents(new EventsDataSource.LoadEventsCallback() {
            @Override
            public void onEventsLoaded(List<Event> events) {

                Iterator<Event> iter = events.iterator();

                while (iter.hasNext()) {
                    Event nextEvent = iter.next();
                    if (!nextEvent.isFood() ||
                            (nextEvent.getTime() < new Date().getTime())) {
                        iter.remove();
                    }
                }

                view.showEvents(events);
            }

            @Override
            public void onError(String error) {
                Log.e("OOPS", "We have an errorrrrr");

            }
        });
    }

    @Override
    public void loadYummyCounts() {

    // Set Pizza Event Count
        final String pizzaSearch = "Pizza".toLowerCase();

        repository.getEvents(new EventsDataSource.LoadEventsCallback() {
            @Override
            public void onEventsLoaded(List<Event> events) {

                Iterator<Event> iter = events.iterator();

                while (iter.hasNext()) {
                    Event nextEvent = iter.next();

                    // Remove event if it doesn't have free food or is in the past
                    // or if the event name or description doesn't contain the search term
                    if (!nextEvent.isFood()
                            || (nextEvent.getTime() < new Date().getTime())
                            || !nextEvent.getDescription().toLowerCase().contains(pizzaSearch)) {
                        iter.remove();
                    }
                }

                view.setPizzaCount(events.size());
            }

            @Override
            public void onError(String error) {
                Log.e("OOPS", "We have an errorrrrr");

            }
        });

        // Set Taco Event Count
        final String tacoSearch = "taco".toLowerCase();

        repository.getEvents(new EventsDataSource.LoadEventsCallback() {
            @Override
            public void onEventsLoaded(List<Event> events) {

                Iterator<Event> iter = events.iterator();

                while (iter.hasNext()) {
                    Event nextEvent = iter.next();

                    // Remove event if it doesn't have free food or is in the past
                    // or if the event name or description doesn't contain the search term
                    if (!nextEvent.isFood()
                            || (nextEvent.getTime() < new Date().getTime())
                            || !nextEvent.getDescription().toLowerCase().contains(tacoSearch)) {
                        iter.remove();
                    }
                }

                view.setTacoCount(events.size());
            }

            @Override
            public void onError(String error) {
                Log.e("OOPS", "We have an errorrrrr");

            }
        });

        // Set Beer Event Count
        final String beerSearch = "beer".toLowerCase();

        repository.getEvents(new EventsDataSource.LoadEventsCallback() {
            @Override
            public void onEventsLoaded(List<Event> events) {

                Iterator<Event> iter = events.iterator();

                while (iter.hasNext()) {
                    Event nextEvent = iter.next();

                    // Remove event if it doesn't have free food or is in the past
                    // or if the event name or description doesn't contain the search term
                    if (!nextEvent.isFood()
                            || (nextEvent.getTime() < new Date().getTime())
                            || !nextEvent.getDescription().toLowerCase().contains(beerSearch)) {
                        iter.remove();
                    }
                }

                view.setBeerCount(events.size());
            }

            @Override
            public void onError(String error) {
                Log.e("OOPS", "We have an errorrrrr");

            }
        });

    }

    @Override
    public void searchEvents(final String searchTerm) {

        if (searchTerm.equalsIgnoreCase("reset")) {
            loadEvents();
            return;
        }

        // Probably better to use Regex
        // http://stackoverflow.com/questions/14018478/string-contains-ignore-case
        final String lowerCaseSearch = searchTerm.toLowerCase();

        repository.getEvents(new EventsDataSource.LoadEventsCallback() {
            @Override
            public void onEventsLoaded(List<Event> events) {

                Iterator<Event> iter = events.iterator();

                while (iter.hasNext()) {
                    Event nextEvent = iter.next();

                    // Remove event if it doesn't have free food or is in the past
                    // or if the event name or description doesn't contain the search term
                    if (!nextEvent.isFood()
                            || (nextEvent.getTime() < new Date().getTime())
                            || !nextEvent.getDescription().toLowerCase().contains(lowerCaseSearch)) {
                        iter.remove();
                    }
                }

                view.showEvents(events);
            }

            @Override
            public void onError(String error) {
                Log.e("OOPS", "We have an errorrrrr");

            }
        });

    }

    @Override
    public void openEventDetails(Event clickedEvent) {

    }

}
