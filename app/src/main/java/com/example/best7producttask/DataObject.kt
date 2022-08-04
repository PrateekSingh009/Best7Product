package com.example.best7producttask


object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(title: String,intialvalue : String ,finalvalue :String) {
        listdata.add(CardInfo(title, intialvalue,finalvalue))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,intialvalue : String ,finalvalue :String)
    {
       // listdata[pos].title=title
        listdata[pos].intialvalue=intialvalue
        listdata[pos].finalvalue = finalvalue
    }

}