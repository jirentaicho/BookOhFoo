const getNewTR = factory();

function addDetail(){
    const target = document.getElementById('form-detail');
    // ここで宣言すれば毎回作成されるので注意
    // const getNewTR = factory();
    target.appendChild(
        (() => getNewTR('detail0.itemId','detail0.count'))()
    );
}

function factory(){
    let count = 0;

    return function(selectDivId,numberDivId){
        count++;
        // ここでのIDは重要でないことに注意してください
        // selectのクローンを作成
        const selectTd = document.createElement('td');
        const selectDiv = document.getElementById(selectDivId);
        const selectClone = selectDiv.cloneNode(true);
        // nameだけ改変する
        const selectName = selectClone.getAttribute('name').replace(/\d+/,count);
        selectClone.setAttribute('name',selectName);
        selectTd.appendChild(selectClone);

        const numberTd = document.createElement('td');
        // numberのクローンを作成
        const numberDiv = document.getElementById(numberDivId);
        const numberClone = numberDiv.cloneNode(true);
        // nameだけ改変する
        const numberName = numberClone.getAttribute('name').replace(/\d+/,count);
        numberClone.setAttribute('name',numberName);
        numberTd.appendChild(numberClone);

        const tr = document.createElement('tr');
        tr.appendChild(selectTd);
        tr.appendChild(numberTd);
        console.log(count);
        return tr;
    }
}