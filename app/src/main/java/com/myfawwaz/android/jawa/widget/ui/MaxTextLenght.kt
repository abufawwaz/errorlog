package com.myfawwaz.android.jawa.widget.ui

class MaxTextLenght {
    fun  MaxTextLenghtText(StringResult: String, maxlenght:Int) {

        if(StringResult.length > maxlenght){
            val StringMax = StringResult.substring(0,5)+".."
        }else {
            val StringMax = StringResult
        }


    }

    companion object {


        fun MaxTextLenghtText(StringResult: String, i: Int): Any {
            var StringMax =""
             if (StringResult.length > i) {
                 StringMax = StringResult.substring(0, i)
            } else {
                StringMax = StringResult
            }
            return StringMax
        }
        fun MaxTextLenghtText(StringResult: String, i: Int,textplus: String): Any {
            var StringMax =""
            if (StringResult.length > i) {
                StringMax = StringResult.substring(0, i-2) + textplus
            } else {
                StringMax = StringResult
            }
            return StringMax
        }
    }
}