package com.app.projectname.utils


object Constants {
    object Alert {
        const val DEFAULT_TITLE: String = "Alert"
        const val DEFAULT_TEXT_POSITIVE: String = "OK"
        const val TEXT_POSITIVE: String = "YES"
        const val TEXT_NEGATIVE: String = "NO"
    }

    object Messages {
        const val DELETE_POST: String = "Are you sure you want to delete this post?"
        const val DISCARD_PROFILE: String = "Are you sure you want to discard the changes?"
        const val DISCARD_IMAGES: String = "All your images will be lost, Continue?"
        const val SIGN_IN: String = "SIGN IN"
        const val TRY_AGAIN: String = "Try Again"
    }

    object UserRole {
        const val ADMIN: String = "3B6D7A50-28B1-4596-9F6B-180EBB2A29D2"
        const val GENERAL: String = "68BC3571-015E-4BE7-9D11-8A06918AA7AF"
        const val SUPER_ADMIN: String = "2689A8DE-7B5D-4D65-B6DA-F2490236A359"
    }

    object Error {
        const val SOMETHING_WENT_WRONG: String = "Something went wrong"
        const val NO_INTERNET: String = "No internet connection"
        const val SELECT_COUNTRY: String = "Select country first"
        const val INVALID_EMAIL: String = "Email is invalid"
    }

    object Bundle {
        const val CARS: String = "Cars"
        const val LANDSCAPE: String = "Landscape"
        const val PAINTING: String = "Painting"


    }

    object DateFormats {
        const val TIME_STAMP_WITH_ZONE: String = "yyyy-MM-dd'T'HH:mm:ss"
        const val YYYY_MM_DD: String = "yyyy-MM-dd"
        const val MMM_YYYY: String = "MMM YYYY"
        const val DD_MMM_YYYY: String = "dd MMM, YYYY"
        const val DD_MMMM_YYYY: String = "dd MMMM, YYYY"
        const val MMM_DD_YYYY: String = "MMM dd, YYYY"
        const val DD_MM_YY: String = "dd/MM/yy"
        const val MM_DD_YYYY: String = "MM/dd/yyyy"
        const val HH_MM_A: String = "hh:mm aa"
        const val H_MM_A: String = "h:mm aa"
        const val HH_MM: String = "HH:mm"
        const val HH_MM_SS: String = "HH:mm:ss"
        const val DD: String = "dd"
        const val MMM: String = "MMM"
        const val LAST_6_MONTHS = 15770000000

    }

    object ResendEmail {
        const val RESEND_TIMER: Long = 30000
        const val INTERVAL: Long = 1000
        const val WAIT_FOR_SECONDS: String = "Please wait for "
    }

    object InterestWheel {
        const val ANGLE: Int = 28
        const val START_ANGLE: Float = -60f
    }



    object Location {
        const val ACTION_LOCATION_PROVIDER_CHANGED: String = "android.location.PROVIDERS_CHANGED"
        const val ENABLE_LOCATION: String =
            "Looks like location is disabled in device settings. Do you want to enable it?"
    }

    object OnBoarding {
        const val NOMADS_VIDEO: String = "//android_asset/nomad_video.mp4"
        const val FREELANCER_VIDEO: String = "//android_asset/freelancer_video.mp4"
        const val ENTREPRENEUR_VIDEO: String = "//android_asset/entrperenuer_video.mp4"
        const val KEY_USERTYPE: String = "UserType"
        const val KEY_CLICKLISTENER: String = "Clicklistener"
        const val NOMADS_ID: String = "{09E6AD4D-9BE1-46E1-BCB1-14C0EE7D0CC7}"
        const val FREELANCER_ID: String = "{C7D11ABF-E7EF-4D17-987F-08963B95AAE6}"
        const val ENTREPRENEUR_ID: String = "{ACFBE693-1715-4A22-843D-D6A627B12FAD}"
    }

    object Splash {
        const val SPLASH_VIDEO: String = "//android_asset/splash_video.mp4"
    }

    object ExoPlayerFullScreen {
        const val BUNDLE_KEY_FEED = "Feed"
    }

    object CreatePost {
        const val FILE_PROVIDER: String = "com.app.neomads.fileprovider"
        const val REQUEST_IMAGE_CAPTURE = 1
        const val IMAGES_COUNT_LIMIT = 5
        const val CHARACTER_COUNT_LIMIT = 300
        const val CHARACTER_COUNT_VISIBLE_COUNT = 100
        const val ONE_VIDEO_LIMIT_MSG = "You can only upload one video per post"
        const val ONE_MEDIA_TYPE_LIMIT_MSG =
            "You can either upload 1 video or up to 10 images per post"
        const val DATETIME_PATTERN = "yyyyMMdd_HHmmss"
        const val FILENAME_PATTERN = "NEOMADS_"
        const val VIDEO_FILE_TYPE = ".mp4"
        const val IMAGE_FILE_TYPE = ".jpeg"
        const val POST_SIZE_MSG = "You have exceeded the maximum post size on NEOMAD (50MB)"
        const val SECTION_KEY = "SECTION"
        const val CreatePostIdentifier = "CreatePost"
        const val SUCCESS_KEY = "ISSUCCESS"
        const val ERROR_KEY = "ISERROR"
        const val UPLOADING_KEY = "ISUPLOADING"
        const val POST_SIZE_LIMIT = 50.0
        const val USER_AGENT = "neomads"
        const val FORMAT_MP4 = "mp4"
        const val FORMAT_3GP = "3gp"
        const val FORMAT_MOV = "mov"
        const val FORMAT_JPG = "jpg"
        const val FORMAT_JPEG = "jpeg"
        const val FORMAT_PNG = "png"
        const val FORMAT_GIF = "gif"
        const val FORMAT_IMAGES_MESSAGE = "Some of the images you selected are not supported by NEOMADS"
        const val FORMAT_VIDEO_MESSAGE = "The video you selected is not supported by NEOMADS"
    }

    //JPG, JPEG, PNG, GIF
    object Paging {
        const val GET_PHOTO_PAGE_SIZE: Int = 20
        const val KEY: String =         "23128582-ec2a8fcbc037558d593509a53"
        const val IMAGE_TYPE_PHOTO: String =         "photo"

    }


}