<template>
    <div class="card-project">
        <div id="shape-card-project">

        </div>
        <div class="card-project-span">
            <span id="card-project-span1">Success</span>
            <span id="card-project-span2">The post has been deleted by an administrator</span>
        </div>
    </div>
</template>

<style>
.card-project {
    display: flex;
}

.card-project-span {
    width: 346px;
    height: 79px;
    background: #FFFFFF;
    border: 1px solid #E2E2EA;
    border-radius: 5px 20px 20px 5px;
    display: flex;
    flex-direction: column;
}

#shape-card-project {
    width: 5px;
    height: 78px;
    background: #82C43C;
}

#card-project-span1 {
    font-family: 'Poppins';
    font-style: normal;
    font-weight: 600;
    font-size: 14px;
    line-height: 21px;
    /* identical to box height */

    letter-spacing: 0.1px;

    color: #171725;
    margin-top: 15px;
    margin-left: 20px;
}

#card-project-span2 {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 24px;
    /* identical to box height, or 171% */

    letter-spacing: 0.1px;

    color: #696974;
    margin-left: 20px;
    margin-bottom: 16px;
}
</style>