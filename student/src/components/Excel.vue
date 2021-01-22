<template>
		<div class="excel">
			<el-upload
			class="upload-demo"
			action=""
			accept=".xls, .xlsx"
			:auto-upload="false"
			:show-file-list="false"
			:on-change="handleChange"
			>
			<el-button
				size="small"
				type="primary"
			>点击上传excel</el-button>
			</el-upload>
		<div class="table">
		<el-table
			v-if="tableData.length > 0"
			:data="tableData"
		>
			<el-table-column
			v-for="item in tableHeader"
			:key="item"
			:prop="item"
			:label="item"
			></el-table-column>
		</el-table>
    </div>
	</div>
</template>

<script>
import xlsx from 'xlsx'

export default {
	data () {
		return {
			tableData: [],
			list:[],
		}
	},
	methods: {
		async handleChange(file, fileList) {
			var data = file.raw;
			const excel = await this.readFile(data);
			// 使用xlsx读取二进制数据 生成excel的全部信息
			var workbook = xlsx.read(excel, { type: "binary" });
			// 通过xlsx转换为可读的表格数据 
			//workbook.Sheets[workbook.SheetNames[0]] 获取当前上传的表格的信息,例如总共有几行几列啥的
			data = xlsx.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[0]]);
			const header = this.getHeaderRow(workbook.Sheets[workbook.SheetNames[0]]);
			this.tableData = data;
            this.tableHeader = header;
            console.log(this.tableHeader);
            console.log(this.tableData);
		},
		// 读取上传的文件
		readFile(file) {
			return new Promise((resolve, reject) => {
				var reader = new FileReader();
				// 把文件转为二进制
				reader.readAsBinaryString(file);
				reader.onload = e => {
				// console.log(e.target.result);
				resolve(e.target.result);
				};
			});
		},
		// 获取表头信息
		getHeaderRow(sheet) {
			const headers = [];
			//获取总共的行数和列数
			const range = xlsx.utils.decode_range(sheet["!ref"]);
			let C;
			const R = range.s.r; // 开始行
			//遍历每列
			for (C = range.s.c; C <= range.e.c; ++C) {
				// 找到每列的第一行数据即使表头
				const cell = sheet[xlsx.utils.encode_cell({ c: C, r: R })];
				let hdr = "UNKNOWN " + C;
				if (cell && cell.t) hdr = xlsx.utils.format_cell(cell);
				headers.push(hdr);
			}
			return headers;
		}
    }
}
</script>