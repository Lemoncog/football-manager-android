curl \
  -F "status=2" \
  -F "notify=1" \
  -F "notes=Some new features and fixed bugs." \
  -F "notes_type=0" \
  -F "ipa=@football-manager/app/build/outputs/apk/app-debug.apk" \
  -H "X-HockeyAppToken: $HOCKEYAPPTOKEN" \
  https://rink.hockeyapp.net/api/2/apps/$HOCKEYFOOTBALLID/app_versions/upload
