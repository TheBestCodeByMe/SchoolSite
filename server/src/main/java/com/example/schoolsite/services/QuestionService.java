package com.example.schoolsite.services;
import com.example.schoolsite.entity.Question;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class QuestionService {
    public static final String COL_NAME = "questionFromUsers";

    public String saveQuestion(Question question) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(question.getCode()).set(question);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Question getQuestionDetails(String code) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(code);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        ;

        DocumentSnapshot document = future.get();

        Question question = null;

        if (document.exists()) {
            question = document.toObject(Question.class);
            return question;
        } else {
            return null;
        }
    }

    public String updateQuestionDetails(Question question) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(question.getCode()).set(question);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteQuestion(String code) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResultApi = dbFirestore.collection(COL_NAME).document(code).delete();

        return "Document with Question code" + code + "has been deleted";
    }
}
