<template>
  <div class="app-container">
    <el-form :model="queryParams" :inline="true" v-show="showSearch">
      <el-form-item label="标题" prop="title"><el-input v-model="queryParams.title" placeholder="请输入" clearable @keyup.enter="handleQuery"/></el-form-item>
      <el-form-item><el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button><el-button icon="Refresh" @click="resetQuery">重置</el-button></el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-button type="primary" icon="Plus" @click="handleAdd" v-hasPermi="['dizang:audio:add']">新增</el-button>
      <el-button type="danger" icon="Delete" :disabled="multiple" @click="handleDelete" v-hasPermi="['dizang:audio:remove']">删除</el-button>
    </el-row>
    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/><el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="标题" prop="title" show-overflow-tooltip/><el-table-column label="分类ID" prop="categoryId" width="80"/>
      <el-table-column label="时长(秒)" prop="duration" width="80"/><el-table-column label="播放" prop="playCount" width="60"/>
      <el-table-column label="操作" width="120"><template #default="scope"><el-button type="text" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['dizang:audio:edit']">修改</el-button><el-button type="text" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['dizang:audio:remove']">删除</el-button></template></el-table-column>
    </el-table>
    <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类ID" prop="categoryId"><el-input-number v-model="form.categoryId"/></el-form-item>
        <el-form-item label="标题" prop="title"><el-input v-model="form.title"/></el-form-item>
        <el-form-item label="描述"><el-input v-model="form.description"/></el-form-item>
        <el-form-item label="封面"><el-input v-model="form.coverUrl"/></el-form-item>
        <el-form-item label="文件URL"><el-input v-model="form.fileUrl"/></el-form-item>
        <el-form-item label="时长(秒)"><el-input-number v-model="form.duration" :min="0"/></el-form-item>
      </el-form>
      <template #footer><el-button @click="cancel">取消</el-button><el-button type="primary" @click="submitForm">确定</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { ref, reactive } from 'vue'
import { list, getInfo, add, update, del } from '@/api/dizang/audio'
import { ElMessage, ElMessageBox } from 'element-plus'

const loading = ref(false); const open = ref(false); const showSearch = ref(true); const title = ref(''); const multiple = ref(false)
const ids = ref([]); const list = ref([]); const total = ref(0); const formRef = ref(null)
const data = reactive({ form: {}, queryParams: { pageNum: 1, pageSize: 10, title: undefined } })
const { form, queryParams } = data
const rules = { title: [{ required: true, message: '标题不能为空', trigger: 'blur' }], categoryId: [{ required: true, message: '分类ID必填', trigger: 'blur' }] }

function getList() { loading.value = true; list(queryParams).then(res => { list.value = res.rows; total.value = res.total; loading.value = false }) }
function resetQuery() { queryParams.title = undefined; handleQuery() }
function handleQuery() { queryParams.pageNum = 1; getList() }
function handleSelectionChange(sel) { ids.value = sel.map(i => i.id); multiple.value = sel.length > 0 }
function cancel() { open.value = false; reset() }
function reset() { Object.assign(form, { id: undefined, categoryId: undefined, title: '', description: '', coverUrl: '', fileUrl: '', duration: 0 }) }
function handleAdd() { reset(); open.value = true; title.value = '新增梵音' }
function handleUpdate(row) { reset(); getInfo(row.id).then(res => { Object.assign(form, res.data); open.value = true; title.value = '修改梵音' }) }
function submitForm() { formRef.value.validate(v => { if (v) { if (form.id) update(form).then(() => { ElMessage.success('修改成功'); open.value = false; getList() }) else add(form).then(() => { ElMessage.success('新增成功'); open.value = false; getList() }) } }) }
function handleDelete(row) { const delIds = row.id ? [row.id] : ids.value; ElMessageBox.confirm('确认删除?', '警告', { type: 'warning' }).then(() => del(delIds.join(',')).then(() => { getList(); ElMessage.success('删除成功') })) }
getList()
</script>