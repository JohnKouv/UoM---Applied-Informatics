# Corporate Bankruptcy Prediction using Machine Learning

A machine learning project developed as part of the **Machine Learning** course at the **University of Macedonia**. This repository presents the implementation, experimental analysis, and report of a **3-member group project** focused on predicting corporate bankruptcy using financial indicators.

---

## Project Overview

The objective of this project was to design and evaluate a complete machine learning pipeline for classifying companies as healthy or bankrupt. Many classification algorithms were trained and compared using standardized evaluation metrics to determine the most effective model for the problem.

---

## Machine Learning Pipeline

```mermaid
flowchart TD
    A[Dataset] --> B[Data Preprocessing]
    B --> C[Min-Max Scaling]
    C --> D[Stratified 4-Fold Cross Validation]
    D --> E[Training Set Balancing]
    E --> F[Model Training]
    F --> G[Performance Evaluation]
    G --> H[Model Comparison]
```
The implemented workflow consists of:

* Data loading and preprocessing
* Missing value detection
* Min-Max feature normalization
* Stratified 4-Fold Cross Validation
* Training set balancing (3:1 healthy-to-bankrupt ratio)
* Model training
* Performance evaluation
* Comparative analysis of all classifiers

---

## Models Evaluated

* Linear Discriminant Analysis (LDA)
* Logistic Regression
* Decision Tree
* Random Forest
* k-Nearest Neighbors (kNN)
* Naïve Bayes
* Support Vector Machine (SVM)
* Gradient Boosting

---

## Evaluation Metrics

The models were evaluated using:

* Accuracy
* Precision
* Recall
* F1-Score
* ROC-AUC

---

## Technologies

* Python
* Pandas
* NumPy
* Google Colab

---

## Sample Results

### Distribution of Healthy and Bankrupt Companies

<p align="center">
  <img src="images/companies_per_year.png" width="700">
</p>

### Financial Feature Statistics

<p align="center">
  <img src="images/feature_statistics.png" width="700">
</p>

### Model Performance Comparison

<p align="center">
  <img src="images/f1_score_comparison.png" width="700">
</p>

### Confusion Matrix (Random Model)

<p align="center">
  <img src="images/random_forest_confusion_matrix.png" width="700">
</p>

---

## Documentation

The complete project documentation is available in the `report/` directory.
