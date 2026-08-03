# Fashion-MNIST Clustering Comparison
### Dimensionality Reduction & Clustering Algorithms

<p align="center">
  <img src="images/comparison_table.png" width="90%">
</p>

![Python](https://img.shields.io/badge/Python-3.11-blue)
![TensorFlow](https://img.shields.io/badge/TensorFlow-Keras-orange)
![Scikit-Learn](https://img.shields.io/badge/scikit--learn-ML-yellow)
![Status](https://img.shields.io/badge/Status-Completed-success)
![License](https://img.shields.io/badge/License-MIT-green)

---

## Project Overview

This project investigates the impact of different **Dimensionality Reduction (DR)** techniques on the performance of clustering algorithms using the **Fashion-MNIST** dataset.

Three feature extraction methods are compared:

- Principal Component Analysis (PCA)
- Stacked Autoencoder (SAE)
- Factor Analysis (FA)

Each reduced representation is evaluated using three clustering algorithms:

- MiniBatch K-Means
- DBSCAN
- Agglomerative Clustering

Performance is assessed using four clustering evaluation metrics:

- Calinski–Harabasz Index
- Davies–Bouldin Index
- Silhouette Score
- Adjusted Mutual Information (AMI)

---

## Team

This was a group project developed as part of the **Machine Learning** course at the **University of Macedonia**.

---

# Dataset

**Fashion-MNIST**

- 70,000 grayscale images
- 10 clothing categories
- Image size: **28×28**
- High-dimensional image classification dataset commonly used for machine learning benchmarking.

---

# Technologies

- Python
- TensorFlow / Keras
- Scikit-Learn
- NumPy
- Pandas
- Matplotlib
- OpenPyXL

---

# Machine Learning Pipeline

```mermaid
flowchart TD

A[Fashion-MNIST Dataset]

A --> B[Data Preprocessing]

B --> C{Dimensionality Reduction}

C --> D[PCA]
C --> E[Stacked Autoencoder]
C --> F[Factor Analysis]

D --> G
E --> G
F --> G

G{Clustering}

G --> H[MiniBatch KMeans]
G --> I[DBSCAN]
G --> J[Agglomerative Clustering]

H --> K
I --> K
J --> K

K[Performance Evaluation]

K --> L[Calinski-Harabasz]

K --> M[Davies-Bouldin]

K --> N[Silhouette Score]

K --> O[Adjusted Mutual Information]
```
---

# Project Results

## SAE Reconstruction

<p align="center">
<img src="images/sae_reconstruction.png" width="85%">
</p>

---

## PCA Explained Variance

<p align="center">
<img src="images/pca_variance.png" width="85%">
</p>

---

## SAE Feature Space

<p align="center">
<img src="images/sae_scatter.png" width="80%">
</p>

---

## Factor Analysis Feature Space

<p align="center">
<img src="images/factor_scatter.png" width="80%">
</p>

---

## Clustering Examples

<p align="center">
<img src="images/clustering_examples.png" width="90%">
</p>

---

## Overall Performance Comparison

<p align="center">
<img src="images/comparison_table.png" width="95%">
</p>

---

# Key Findings

- MiniBatch K-Means consistently achieved the strongest clustering performance.
- Stacked Autoencoder (SAE) produced the most informative latent representation.
- DBSCAN struggled on the Fashion-MNIST feature space regardless of the dimensionality reduction technique.
- Agglomerative Clustering delivered competitive performance but generally remained behind MiniBatch K-Means.
- No single combination dominated every evaluation metric, highlighting the importance of selecting algorithms according to the desired clustering objective.

---

# Future Improvements

- Hyperparameter optimization
- UMAP comparison
- t-SNE visualization
- HDBSCAN clustering
- CNN-based Autoencoder
- Interactive dashboards using Plotly
- Experiment tracking with MLflow

---

# Report

The complete technical report describing the methodology, experiments and analysis can be found in:

```
reports/MachineLearning_Report.pdf
```
