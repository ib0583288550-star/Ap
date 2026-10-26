# מנגינה — הפרדת שירה אופליין

אפליקציית Android בעברית שמטרתה להפריד שירה מהמנגינה באמצעות מודל AI מקומי.

## כיוון טכני
- Android / Kotlin
- בחירת קובץ שמע דרך Android Storage Access Framework
- הפרדת מקורות באמצעות Demucs
- יצוא instrumental לקובץ WAV
- המטרה היא עיבוד מקומי ללא העלאת השיר לשרת

## סטטוס
המאגר אותחל. נדרשת אינטגרציה ובדיקת מנוע ההפרדה בפועל לפני הפצת APK.

## ייחוס
Demucs: https://github.com/facebookresearch/demucs
Android reference implementation: https://github.com/sevagh/demucs-android
