<template>
  <div class="app-container">
    <el-form :model="queryParams" :inline="true" v-show="showSearch">
      <el-form-item label="名称" prop="title"><el-input v-model="queryParams.title" placeholder="请输入" clearable @keyup.enter="handleQuery"/></el-form-item>
      <el-form-item label="分类" prop="category"><el-select v-model="queryParams.category" placeholder="请选择" clearable><el-option label="经" value="经"/><el-option label="律" value="律"/><el-option label="论" value="论"/><el-option label="其他" value="其他"/></el-select></el-form-item>
      <el-form-item><el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button><el-button icon="Refresh" @click="resetQuery">重置</el-button></el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-button type="primary" icon="Plus" @click="handleAdd" v-hasPermi="['dizang:classic:add']">新增</el-button>
      <el-button type="danger" icon="Delete" :disabled="multiple" @click="handleDelete" v-hasPermi="['dizang:classic:remove']">删除</el-button>
    </el-row>
    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/><el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="名称" prop="title"/><el-table-column label="分类" prop="category" width="80"/><el-table-column label="简介" prop="description" show-overflow-tooltip/>
      <el-table-column label="操作" width="120"><template #default="scope"><el-button type="text" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['dizang:classic:edit']">修改</el-button><el-button type="text" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['dizang:classic:remove']">删除</el-button></template></el-table-column>
    </el-table>
    <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog :title="title" v-model="open" width="700px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="title"><el-input v-model="form.title"/></el-form-item>
        <el-form-item label="分类" prop="category"><el-select v-model="form.category"><el-option label="经" value="经"/><el-option label="律" value="律"/><el-option label="论" value="论"/><el-option label="其他" value="其他"/></el-select></el-form-item>
        <el-form-item label="简介"><el-input v-model="form.description" type="textarea" :rows="3"/></el-form-item>
        <el-form-item label="封面"><el-input v-model="form.coverUrl"/></el-form-item>
      </el-form>
      <template #footer><el-button @click="cancel">取消</el-button><el-button type="primary" @click="submitForm">确定</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { ref, reactive } from 'vue'
import { list, getInfo, add, update, del } from '@/api/dizang/classic'
import { ElMessage, ElMessageBox } from 'element-plus'

const loading = ref(false); const open = ref(false); const showSearch = ref(true); const title = ref(''); const multiple = ref(false)
const ids = ref([]); const list = ref([]); const total = ref(0); const formRef = ref(null)
const data = reactive({ form: {}, queryParams: { pageNum: 1, pageSize: 10, title: undefined, category: undefined } })
const { form, queryParams } = data
const rules = { title: [{ required: true, message: '名称不能为空', trigger: 'blur' }] }

function getList() { loading.value = true; list(queryParams).then(res => { list.value = res.rows; total.value = res.total; loading.value = false }) }
function resetQuery() { queryParams.title = undefined; queryParams.category = undefined; handleQuery() }
function handleQuery() { queryParams.pageNum = 1; getList() }
function handleSelectionChange(sel) { ids.value = sel.map(i => i.id); multiple.value = sel.length > 0 }
function cancel() { open.value = false; reset() }
function reset() { Object.assign(form, { id: undefined, title: '', description: '', coverUrl: '', category: '经' }) }
function handleAdd() { reset(); open.value = true; title.value = '新增经典' }
function handleUpdate(row) { reset(); getInfo(row.id).then(res => { Object.assign(form, res.data); open.value = true; title.value = '修改经典' }) }
function submitForm() { formRef.value.validate(v => { if (v) { if (form.id) update(form).then(() => { ElMessage.success('修改成功'); open.value = false; getList() }) else add(form).then(() => { ElMessage.success('新增成功'); open.value = false; getList() }) } }) }
function handleDelete(row) { const delIds = row.id ? [row.id] : ids.value; ElMessageBox.confirm('确认删除?', '警告', { type: 'warning' }).then(() => del(delIds.join(',')).then(() => { getList(); ElMessage.success('删除成功') })) }
getList()
</script>