<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  props: {
    label: { type: String, required: true },
    placeholder: { type: String, required: true },
    type: { type: String, required: true, default: 'text' },
    value: String
  },
  emits: ['update:value']
})
</script>

<template>
    <div class="form-input">
        <label for="">{{ label }}</label>
        <input :type="type" :placeholder="placeholder" style="margin-top: 6px" :value="value" @input="$emit('update:value', ($event as any).target.value)">
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
    color: #8181A5;
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