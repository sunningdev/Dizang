<template>
  <div class="app-container">
    <el-form :model="queryParams" :inline="true" v-show="showSearch">
      <el-form-item label="昵称" prop="nickname"><el-input v-model="queryParams.nickname" placeholder="请输入" clearable @keyup.enter="handleQuery"/></el-form-item>
      <el-form-item label="内容" prop="content"><el-input v-model="queryParams.content" placeholder="请输入" clearable @keyup.enter="handleQuery"/></el-form-item>
      <el-form-item label="状态" prop="auditStatus">
        <el-select v-model="queryParams.auditStatus" placeholder="请选择" clearable><el-option label="待审核" :value="0"/><el-option label="已通过" :value="1"/><el-option label="已拒绝" :value="2"/></el-select>
      </el-form-item>
      <el-form-item label="时间"><el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始" end-placeholder="结束" value-format="YYYY-MM-DD"/></el-form-item>
      <el-form-item><el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button><el-button icon="Refresh" @click="resetQuery">重置</el-button></el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-button type="danger" icon="Delete" :disabled="multiple" @click="handleDelete" v-hasPermi="['dizang:blessing:remove']">删除</el-button>
    </el-row>
    <el-table v-loading="loading" :data="list" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/><el-table-column label="ID" prop="id" width="80"/>
      <el-table-column label="昵称" prop="nickname" width="100"/>
      <el-table-column label="内容" prop="content" show-overflow-tooltip/>
      <el-table-column label="点赞" prop="likeCount" width="60"/>
      <el-table-column label="状态" width="90"><template #default="scope"><el-tag v-if="scope.row.auditStatus===0" type="warning">待审核</el-tag><el-tag v-else-if="scope.row.auditStatus===1" type="success">已通过</el-tag><el-tag v-else type="danger">已拒绝</el-tag></template></el-table-column>
      <el-table-column label="提交时间" prop="createTime" width="160"/>
      <el-table-column label="操作" width="160"><template #default="scope">
        <el-button type="text" icon="CircleCheck" @click="handleApprove(scope.row)" v-if="scope.row.auditStatus===0" v-hasPermi="['dizang:blessing:audit']">通过</el-button>
        <el-button type="text" icon="CircleClose" @click="handleReject(scope.row)" v-if="scope.row.auditStatus===0" v-hasPermi="['dizang:blessing:audit']">拒绝</el-button>
        <el-button type="text" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['dizang:blessing:remove']">删除</el-button>
      </template></el-table-column>
    </el-table>
    <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList"/>

    <el-dialog title="拒绝原因" v-model="rejectOpen" width="400px" append-to-body>
      <el-form :model="rejectForm"><el-form-item label="原因"><el-input v-model="rejectForm.rejectReason" type="textarea" :rows="3"/></el-form-item></el-form>
      <template #footer><el-button @click="rejectOpen=false">取消</el-button><el-button type="primary" @click="submitReject">确定拒绝</el-button></template>
    </el-dialog>
  </div>
</template>
<script setup>
import { ref, reactive } from 'vue'
import { listBlessing, delBlessing, approveBlessing, rejectBlessing } from '@/api/dizang/blessing'
import { ElMessage, ElMessageBox } from 'element-plus'

const loading = ref(false); const showSearch = ref(true); const multiple = ref(false); const rejectOpen = ref(false);
const ids = ref([]); const list = ref([]); const total = ref(0); const dateRange = ref([])
const currentRow = ref({})
const rejectForm = reactive({ id: undefined, rejectReason: '' })
const queryParams = reactive({ pageNum: 1, pageSize: 10, nickname: undefined, content: undefined, auditStatus: undefined })

function getList() {
  loading.value = true; const params = { ...queryParams }
  if (dateRange.value && dateRange.value.length === 2) { params.params = { beginTime: dateRange.value[0], endTime: dateRange.value[1] } }
  listBlessing(params).then(res => { list.value = res.rows; total.value = res.total; loading.value = false })
}
function resetQuery() { queryParams.nickname = undefined; queryParams.content = undefined; queryParams.auditStatus = undefined; dateRange.value = []; handleQuery() }
function handleQuery() { queryParams.pageNum = 1; getList() }
function handleSelectionChange(sel) { ids.value = sel.map(i => i.id); multiple.value = sel.length > 0 }
function handleDelete(row) { const delIds = row.id ? [row.id] : ids.value; ElMessageBox.confirm('确认删除?', '警告', { type: 'warning' }).then(() => delBlessing(delIds.join(',')).then(() => { getList(); ElMessage.success('删除成功') })) }
function handleApprove(row) { approveBlessing(row.id).then(() => { ElMessage.success('审核通过'); getList() }) }
function handleReject(row) { currentRow.value = row; rejectForm.id = row.id; rejectForm.rejectReason = ''; rejectOpen.value = true }
function submitReject() { rejectBlessing(rejectForm).then(() => { ElMessage.success('已拒绝'); rejectOpen.value = false; getList() }) }
getList()
</script>