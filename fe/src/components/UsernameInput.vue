<template>
    <div class="form-input">
        <label for="">Username</label>
        <input type="text" placeholder="Start typing..." style="margin-top: 6px">
        <div class="bg">
            <hr>
        </div>
    </div>
</template>

<style>
body {
    padding: 0;
    margin: 0;
}

.form-input {
    display: flex;
    width: 508px;
    height: 72px;
    flex-direction: column;
}

.form-input label {
    font-family: 'Lato';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    width: 63px;
    height: 21px;
}

.form-input input {
    font-family: 'Lato';
    font-style: normal;
    font-weight: 700;
    font-size: 14px;
    padding: 0;
    border: none;
    background: none;
    outline: none;
    height: 17px;
    line-height: 16.8px;
}

.form-input .bg hr{
    text-overflow: ellipsis;
    width: 508px;
    height: 1px;
    margin-top: 17px;
    opacity: 0.2
}
</style>