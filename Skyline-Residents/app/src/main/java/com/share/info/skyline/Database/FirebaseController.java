package com.share.info.skyline.Database;

//TODO: Make sure async behaviour doesnt make fetch behave wierdly

public class FirebaseController  {

//    private FirebaseFirestore firebaseFirestore;
//    private DocumentReference documentReference;
//    private String ANNOUNCEMENTS;
//
//    public FirebaseController(String condoId) {
//        firebaseFirestore = FirebaseFirestore.getInstance();
//        documentReference = firebaseFirestore.document(condoId);
//    }
//
//    @Override
//    public List<Announcement> fetchAnnouncements() {
//
//        List<Announcement> announcementList = new ArrayList<>();
//
//        List<DocumentSnapshot> documentSnapshots = this.fetchCondoFeature(ANNOUNCEMENTS);
//        for (DocumentSnapshot documentSnapshot: documentSnapshots) {
//            announcementList.add(documentSnapshot.toObject(Announcement.class));
//        }
//
//        return announcementList;
//
//    }
//
//    @Override
//    public List<Event> fetchEvents() {
//        List<Event> eventList = new ArrayList<>();
//
//        List<DocumentSnapshot> documentSnapshots = this.fetchCondoFeature(ANNOUNCEMENTS);
//        for (DocumentSnapshot documentSnapshot: documentSnapshots) {
//            eventList.add(documentSnapshot.toObject(Event.class));
//        }
//
//        return eventList;
//    }
//
//    @Override
//    public List<Amenity> fetchAmenities() {
//        List<Amenity>  amenityList = new ArrayList<>();
//
//        List<DocumentSnapshot> documentSnapshots = this.fetchCondoFeature(ANNOUNCEMENTS);
//        for (DocumentSnapshot documentSnapshot: documentSnapshots) {
//            amenityList.add(documentSnapshot.toObject(Amenity.class));
//        }
//
//        return amenityList;
//    }
//
//    private List<DocumentSnapshot> fetchCondoFeature(final String feature) {
//
//        final List<DocumentSnapshot> condoFeatureList = new ArrayList<>();
//
//        documentReference
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//                        if (documentSnapshot.exists()) {
//
//                            documentReference.collection(feature)
//                                    .get()
//                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                        @Override
//                                        public void onSuccess(QuerySnapshot documentSnapshots) {
//
//                                            if(!documentSnapshots.isEmpty()) {
//
//                                                for (DocumentSnapshot featureSnapshot : documentSnapshots) {
//                                                   condoFeatureList.add(featureSnapshot);
//                                                }
//                                            }
//                                        }
//                                    });
//
//                            Log.d("SUCCESS", "Document retrieved successfully");
//
//                        } else {
//
//
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Log.w("ERROR", e.toString());
//
//                    }
//                });
//
//        return condoFeatureList;
//
//    }
}
