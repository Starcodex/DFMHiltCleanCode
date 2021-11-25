package com.mfcc.hilt.feature_movies.failure

import com.mfcc.hilt.core.common.Failure
import com.mfcc.hilt.core.common.Failure.*

class MovieFailure {
    class ListNotAvailable : FeatureFailure()
    class NonExistentMovie : FeatureFailure()
}