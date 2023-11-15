<template>
  <div class="administrator">
    <div class="img-adm">
      <img
        src="https://thewitcher3.wiki.fextralife.com/file/The-Witcher-3/geralt-of-rivia-icon-the-witcher-3-wild-hunt-wiki-guide.png"
        alt=""
      />
    </div>
    <div class="adm-text">
      <span id="adm1">Administrator</span>
      <br />
      <span id="adm2">@admin</span>
    </div>
  </div>
</template>

<style>
.administrator {
  display: flex;
  flex-direction: row;
  align-items: center;
  background: #f3f3f8;
  border: 1px #e2e2ea;
  border-radius: 15px;
  width: 230px;
  height: 64px;
}

.administrator .img-adm img {
  width: 32px;
  height: 32px;
  border-radius: 45%;
  margin-left: 12px;
}

.adm-text {
  font-family: 'Poppins';
  font-style: normal;
  font-weight: 600;
  font-size: 14px;
  line-height: 21px;
  height: 40px;
  flex-direction: column;
  margin-left: 10px;
}

.adm-text #adm1 {
  font-family: 'Poppins';
  font-style: normal;
  font-weight: 600;
  font-size: 14px;
  line-height: 21px;
  letter-spacing: 0.1px;
  color: #171725;
}

.adm-text #adm2 {
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 400;
  font-size: 12px;
  line-height: 14px;
  color: #92929d;
}
</style>
